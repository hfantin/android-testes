package br.com.alura.agenda.database;

import static br.com.alura.agenda.database.AgendaMigrations.TODAS_MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.alura.agenda.database.converters.LocalDateTimeConverter;
import br.com.alura.agenda.database.converters.TipoTelefoneConverter;
import br.com.alura.agenda.database.dao.AlunoDao;
import br.com.alura.agenda.database.dao.TelefoneDao;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({LocalDateTimeConverter.class, TipoTelefoneConverter.class})
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String BANCO_DE_DADOS_ALUNO = "aluno.db";

    public abstract AlunoDao getAlunoDao();

    public abstract TelefoneDao getTelefoneDao();

    public static AgendaDatabase getInstance(Context context) {
        return Room
                .databaseBuilder(context, AgendaDatabase.class, BANCO_DE_DADOS_ALUNO)
//                .allowMainThreadQueries()
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }
}
