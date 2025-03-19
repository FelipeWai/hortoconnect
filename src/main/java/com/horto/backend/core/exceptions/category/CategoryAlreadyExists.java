package com.horto.backend.core.exceptions.category;

public class CategoryAlreadyExists extends RuntimeException {
      public CategoryAlreadyExists(String s) {
          super("Categoria com nome " + s + " já existe");
      }
}
