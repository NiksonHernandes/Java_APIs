package br.com.sevencomm.carros.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MsgError {
    public String error;
}