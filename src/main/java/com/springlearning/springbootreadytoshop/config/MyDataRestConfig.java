package com.springlearning.springbootreadytoshop.config;

import com.springlearning.springbootreadytoshop.entity.Product;
import com.springlearning.springbootreadytoshop.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnSupportedActions = {HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT};

    //disable Http methods for Product: POST, PUT, DELETE
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnSupportedActions))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnSupportedActions)));

        //disable Http methods for ProductCategory: POST, PUT, DELETE
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnSupportedActions))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnSupportedActions)));
    }
}
