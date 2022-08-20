package com.springlearning.springbootreadytoshop.config;

import com.springlearning.springbootreadytoshop.entity.Country;
import com.springlearning.springbootreadytoshop.entity.Product;
import com.springlearning.springbootreadytoshop.entity.ProductCategory;
import com.springlearning.springbootreadytoshop.entity.State;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    public MyDataRestConfig(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnSupportedActions = {HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT};

        //disable Http methods for: POST, PUT, DELETE
        disableHttpMethods(Product.class,config, theUnSupportedActions);
        disableHttpMethods(ProductCategory.class,config, theUnSupportedActions);
        disableHttpMethods(Country.class,config, theUnSupportedActions);
        disableHttpMethods(State.class,config, theUnSupportedActions);

        //call an internal helper method
        exposeIds(config);
    }

    private static void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnSupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnSupportedActions))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnSupportedActions)));
    }

    private void exposeIds(RepositoryRestConfiguration config) {

        //expose entity ids

        //get a list of entity classes
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        List<Class> entityClasses = new ArrayList<>();

        for (EntityType tempEntityType: entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        //expose entity ids
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
