package es.cesguiro;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Override
    public void getAll() {
        LoggerFactory.getLogger(ProductService.class).warn("Getting all products");
    }
}
