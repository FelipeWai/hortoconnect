package com.horto.backend.core.exceptions.category;

public class CategoryAlreadyExists extends RuntimeException {
      public CategoryAlreadyExists(String s) {
          super("Category with name " + s + " already exists");
      }
}
