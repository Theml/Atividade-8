package src.main.java;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação mock do serviço de notificação para uso em testes
 * Permite capturar notificações para validar em testes
 */
public class MockNotificacaoService implements NotificacaoService {
    private List<String> notificacoes = new ArrayList<>();
    
    @Override
    public void notificar(String destinatario, String mensagem) {
        notificacoes.add(String.format("[NOTIFICAÇÃO para %s]: %s", destinatario, mensagem));
    }
    
    public List<String> getNotificacoes() {
        return new ArrayList<>(notificacoes);
    }
    
    public void limparNotificacoes() {
        notificacoes.clear();
    }
}