package br.com.alura.aluraviagens.model

import java.io.Serializable
import java.math.BigDecimal

data class Pacote(val local: String,
                  val imagem: Int,
                  val dias: Int,
                  val preco: BigDecimal) : Serializable