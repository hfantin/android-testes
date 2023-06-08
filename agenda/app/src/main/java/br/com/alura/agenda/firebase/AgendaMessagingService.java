package br.com.alura.agenda.firebase;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Map;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.dto.AlunoSync;
import br.com.alura.agenda.event.AtualizaListaAlunoEvent;

/**
 * Created by hamilton on 21/03/17.
 */

public class AgendaMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> mensagem = remoteMessage.getData();
        Log.i("Mensagem FCM", "" + mensagem);
        converteParaAluno(mensagem);

    }

    private void converteParaAluno(Map<String, String> mensagem) {
        String chave = "alunoSync";

        if(mensagem.containsKey(chave)){
            String json = mensagem.get(chave);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                AlunoSync alunoSync = objectMapper.readValue(json, AlunoSync.class);
                AlunoDAO alunoDAO = new AlunoDAO(this);
                alunoDAO.sincroniza(alunoSync.getAlunos());
                alunoDAO.close();
                EventBus.getDefault().post(new AtualizaListaAlunoEvent());
                Log.i("alunoSync", alunoSync.toString());
            } catch (IOException e) {
                Log.e("erro", e.getMessage());
            }
        }

    }
}
