package com.svetanis.adk.a2aserver.agent;

import java.util.HashMap;
import java.util.Map;

import com.google.adk.tools.Annotations.Schema;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;

/**
 * External function tools used by the ProductCatalogAgent.
 * Provides a method to fetch real-time product information from a mock catalog.
 */
public class ProductCatalogTools {

  private static final ImmutableMap<String, String> CATALOG = productCatalog();
  private static final String DESC = "Name of the product (e.g. 'iPhone 15 Pro')";

  @Schema(description = "Get product information for a given product.")
  public static Map<String, Object> getProductInfo(//
      @Schema(description = DESC, name = "productName") String productName) {
    String key = productName.toLowerCase().trim();
    if (CATALOG.containsKey(key)) {
      String productInfo = String.format("Product: %s", CATALOG.get(key));
      Map<String, Object> map = new HashMap<>();
      map.put("status", "success");
      map.put("productInfo", productInfo);
      return map;
    }
    String joined = Joiner.on(", ").join(CATALOG.keySet());
    String msg = "Sorry, I don't have information for %s. Available products: %s";
    Map<String, Object> map = new HashMap<>();
    map.put("status", "error");
    map.put("errorMessage", String.format(msg, productName, joined));
    return map;
  }

  // Mock product catalog
  private static ImmutableMap<String, String> productCatalog() {
    Map<String, String> map = new HashMap<>();
    map.put("iphone 15 pro", "iPhone 15 Pro, $999, Low Stock (8 units), 128GB, Titanium finish");
    map.put("samsung galaxy s24", "Samsung Galaxy S24, $799, In Stock (31 units), 256GB, Phantom Black");
    map.put("dell xps 15", "'Dell XPS 15, $1,299, In Stock (45 units), 15.6\" display, 16GB RAM, 512GB SSD'");
    map.put("macbook pro 14", "'MacBook Pro 14\", $1,999, In Stock (22 units), M3 Pro chip, 18GB RAM, 512GB SSD'");
    map.put("sony wh-1000xm5", "Sony WH-1000XM5 Headphones, $399, In Stock (67 units), Noise-canceling, 30hr battery");
    map.put("ipad air", "'iPad Air, $599, In Stock (28 units), 10.9\" display, 64GB'");
    map.put("lg ultrawide 34", "'LG UltraWide 34\" Monitor, $499, Out of Stock, Expected: Next week'");
    return ImmutableMap.copyOf(map);
  }
}
