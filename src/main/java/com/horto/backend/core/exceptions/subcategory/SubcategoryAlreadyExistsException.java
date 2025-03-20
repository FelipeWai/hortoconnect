package com.horto.backend.core.exceptions.subcategory;

public class SubcategoryAlreadyExistsException extends RuntimeException {
  public SubcategoryAlreadyExistsException(String message) {
    super("Subcategoria com nome " + message + " já existe");
  }
}
