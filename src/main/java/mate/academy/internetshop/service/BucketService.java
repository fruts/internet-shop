package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Product;

public interface BucketService {
    Bucket addItem(Long bucketId, Product product);
}
