package br.com.alura.aluraviagens

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun BigDecimal.formatoMoeda(): String{
    val formatoMoeda = DecimalFormat.getCurrencyInstance(
            Locale("pt", "br"));
    return formatoMoeda.format(this).replace("R$", "R$ ")
}


fun Int.formataUnidade(unidade: String): String{
    var descricaoDias = "$this $unidade"
    if(this > 1) descricaoDias += "s"
    return descricaoDias
}


fun Calendar.formatoDiaMes(): String{
    val formato = SimpleDateFormat("dd/MM")
    return formato.format(this.time)
}


fun Int.periodoEntreDatas(): String {
    val dataIda = Calendar.getInstance()
    val dataVolta = Calendar.getInstance()
    dataVolta.add(Calendar.DATE, this)
    val periodoTexto = "${dataIda.formatoDiaMes()} - ${dataVolta.formatoDiaMes()} de ${dataVolta.get(Calendar.YEAR)}"
    return periodoTexto
}

