package br.com.entrega_mais.transportadora.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transportadora implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String email;

    @Transient
    private String password;

    @NotEmpty(message = "O nome da empresa é obrigatório!")
    private String nm_empresa;

    @NotEmpty(message = "O nome do responsável é obrigatório!")
    private String nm_resp;

    //@CNPJ
    @NotBlank(message = "CNPJ é obrigatório!")
    @Digits(integer = 14, fraction = 0, message = "Informe um CNPJ válido")
    private String cnpj;

    private String telefone;

    private String setor;

    private boolean cobra_embarque = false;

    private String pix;

    private String idusuario;

}
