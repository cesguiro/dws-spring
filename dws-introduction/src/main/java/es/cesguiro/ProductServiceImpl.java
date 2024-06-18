package es.cesguiro;


import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService{

    @Override
    public void getAll() {
        LogManager.getLogger().warn("Getting all products");
    }
}
