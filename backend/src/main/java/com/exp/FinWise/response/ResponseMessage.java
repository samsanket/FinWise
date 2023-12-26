/*
 * Copyright (c) 2023.
 * Sanket Deshpande . All rights reserved
 */

package com.exp.FinWise.response;

public class ResponseMessage {
  private String message;

  public ResponseMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
