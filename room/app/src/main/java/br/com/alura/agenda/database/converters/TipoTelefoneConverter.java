package br.com.alura.agenda.database.converters;

import androidx.room.TypeConverter;

import br.com.alura.agenda.model.TipoTelefone;

public class TipoTelefoneConverter {

    @TypeConverter
    public String paraString(TipoTelefone value) {
        if(value != null) {
            return value.name();
        }
        return TipoTelefone.FIXO.name();
    }

    @TypeConverter
    public TipoTelefone paraTipoTelefone(String value) {
        if(value!=null) {
            return TipoTelefone.valueOf(value);
        }
        return TipoTelefone.FIXO;

    }
}
