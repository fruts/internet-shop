package jv.internetshop.service.impl;

import java.util.List;
import jv.internetshop.dao.ProductDao;
import jv.internetshop.lib.Inject;
import jv.internetshop.lib.Service;
import jv.internetshop.model.Product;
import jv.internetshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product getByUserId(Long id) {
        return productDao.get(id).get();
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Long id) {
        return productDao.delete(id);
    }
}
