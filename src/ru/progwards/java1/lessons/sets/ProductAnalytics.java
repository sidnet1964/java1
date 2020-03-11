package ru.progwards.java1.lessons.sets;

import java.util.*;

public class ProductAnalytics {
    private List<Shop> shops;
    private List<Product> products;
    public ProductAnalytics(List<Product> products, List<Shop> shops){
        this.products = new ArrayList();
        this.products.addAll(products);
        this.shops = new ArrayList();
        this.shops.addAll(shops);
    }
//  товары из products, которые имеются во всех магазинах
    public Set<Product> existInAll(){
        Set<Product> set1 = new HashSet<>();    //  для результата
        Iterator<Shop> iter = this.shops.iterator();
        while (iter.hasNext()) {
            Shop s00 = iter.next();     //  очередной магазин
            if (set1.isEmpty())         //  в пустое множество добавить продукты первого магазина
                set1 = new HashSet(s00.getProducts());
            else
                set1.retainAll(s00.getProducts());  //  пересечение с продуктами остальных магазинов
        }
        return set1;
    }
    //  товары из products, которые имеются хотя бы в одном магазине
    public Set<Product> existAtListInOne() {
        Set<Product> set1 = new HashSet<>();    //  для результата
        Iterator<Shop> iter = this.shops.iterator();
        while (iter.hasNext()) {
            Shop s00 = iter.next();     //  очередной магазин
            if (set1.isEmpty())         //  в пустое множество добавить продукты первого магазина
                set1 = new HashSet(s00.getProducts());
            else
                set1.addAll(s00.getProducts());  //  пересечение с продуктами остальных магазинов
        }
        return set1;
    }
//  товары из products, которых нет ни в одном магазине
    public Set<Product> notExistInShops(){
        Set<Product> set1 = new HashSet(this.products);    //  для результата - список продуктов полный
        set1.removeAll(this.existAtListInOne());    //  из полного списка исключить присутствующие
        return set1;
    }
//  товары из products, которые есть только в одном магазине
    public Set<Product> existOnlyInOne(){
        Set<Product> set1 = this.existAtListInOne();    //  для результата - список продуктов присутствующих
        Iterator<Product> it1 = set1.iterator();    //  итератор по продуктам
        while (it1.hasNext()) {
            Product p00 = it1.next();     //  очередной продукт
            Iterator<Shop> it2 = this.shops.iterator(); //  итератор по магазинам
            int count = 0;
            while (it2.hasNext()) {
                Shop s00 = it2.next();     //  очередной магазин
                if (s00.getProducts().contains(p00))
                    count++;
            }
            if (count > 1)
                it1.remove();
        }
        return set1;
    }
    public static void main(String[] args) {
//        String [] aProd = {"01","02","03","11","12","13","21","22","23","21","32","33","41","42","43"};
//        List<String> lProd = Arrays.asList(aProd);
        Product p01 = new Product("01");
        Product p02 = new Product("02");
        Product p03 = new Product("03");
        Product p04 = new Product("04");
        Product p05 = new Product("05");
        Product p06 = new Product("06");
        Product p07 = new Product("07");
        Product p08 = new Product("08");
        Product p09 = new Product("09");
        List<Product> l00 = Arrays.asList(p01, p02, p03, p04, p05, p06, p07, p08, p09);
        List<Product> l10 = Arrays.asList(p01, p02, p03, p08);
        List<Product> l20 = Arrays.asList(p03, p04, p05, p08);
        List<Product> l30 = Arrays.asList(p05, p06, p07, p08);
        Shop s10 = new Shop(l10);
        Shop s20 = new Shop(l20);
        Shop s30 = new Shop(l30);
        List<Shop> s00 = Arrays.asList(s10, s20, s30);
        System.out.println(p01.getCode());
        System.out.println(s10.getProducts());
        ProductAnalytics pax = new ProductAnalytics(l00, s00);
        System.out.println(pax.existInAll());
        System.out.println(pax.existAtListInOne());
        System.out.println(pax.notExistInShops());
        System.out.println(pax.existOnlyInOne());
    }
}
