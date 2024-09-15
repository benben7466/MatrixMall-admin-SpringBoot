package com.bzq.matrixmall.service.product.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.common.model.KeyValue;
import com.bzq.matrixmall.converter.product.ProdProductImageConverter;
import com.bzq.matrixmall.mapper.product.ProdProductImageMapper;
import com.bzq.matrixmall.model.entity.product.ProdProductCategory;
import com.bzq.matrixmall.model.entity.product.ProdProductImage;
import com.bzq.matrixmall.service.product.ProdProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdProductImageServiceImpl extends ServiceImpl<ProdProductImageMapper, ProdProductImage> implements ProdProductImageService {
    private final ProdProductImageConverter prodProductImageConverter;

    @Override
    public void saveProductImage(Long productId, List<String> imageUrls) {
        if (productId == null || CollectionUtil.isEmpty(imageUrls)) {
            return;
        }

        // 原ID集合
        List<String> oldRows = this.list(new LambdaQueryWrapper<ProdProductImage>()
                        .eq(ProdProductImage::getProductId, productId))
                .stream()
                .map(ProdProductImage::getImageUrl)
                .collect(Collectors.toList());

        // 新增
        List<String> addRows;
        if (CollectionUtil.isEmpty(oldRows)) {
            addRows = imageUrls;
        } else {
            addRows = imageUrls.stream()
                    .filter(curRow -> !oldRows.contains(curRow))
                    .collect(Collectors.toList());
        }

        for (String row : addRows) {
            try {
                URL url = new URL(row);
                BufferedImage image = ImageIO.read(url);

                ProdProductImage img = new ProdProductImage(productId, row, image.getWidth(), image.getHeight());
                this.save(img);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 删除
        if (CollectionUtil.isNotEmpty(oldRows)) {
            List<String> removeRows = oldRows.stream()
                    .filter(curRow -> !imageUrls.contains(curRow))
                    .collect(Collectors.toList());

            if (CollectionUtil.isNotEmpty(removeRows)) {
                this.remove(new LambdaQueryWrapper<ProdProductImage>()
                        .eq(ProdProductImage::getProductId, productId)
                        .in(ProdProductImage::getImageUrl, removeRows)
                );
            }
        }
    }

    @Override
    public void deleteProductImage(Long productId) {
        this.remove(new LambdaQueryWrapper<ProdProductImage>()
                .eq(ProdProductImage::getProductId, productId)
        );
    }

    //取得商品图片
    @Override
    public List<String> getProductImageData(Long prodId) {
        // 查询数据
        List<ProdProductImage> prodImageList = this.list(
                new LambdaQueryWrapper<ProdProductImage>()
                        .eq(ProdProductImage::getProductId, prodId)
                        .orderByAsc(ProdProductImage::getId)
        );

        List<String> transformedList = prodImageList
                .stream()
                .map(entry -> entry.getImageUrl())
                .toList();

        return transformedList;
    }

    //通过商品ID集合，取得图片集合
    @Override
    public List<ProdProductImage> getProductImageByPids(List<Long> productIds) {
        // 查询数据
        List<ProdProductImage> prodCategoryList = this.list(
                new LambdaQueryWrapper<ProdProductImage>()
                        .in(ProdProductImage::getProductId, productIds)
                        .orderByAsc(ProdProductImage::getId)
        );

        return prodCategoryList;
    }
}
