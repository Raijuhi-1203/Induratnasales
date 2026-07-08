package codesgesture.app.induratnasales.interfaces;

import codesgesture.app.induratnasales.Models.ProductModel;

public interface Notify {
    void onAdd(ProductModel data);
    void onRemove(ProductModel data);
}
