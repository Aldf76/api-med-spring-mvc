package med.voll.api.medico;

//Declaração da classe como um "record" do Java, uma forma concisa de criar classes imutáveis
public record DadosMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

 // Construtor adicional que aceita um objeto Medico e cria um objeto DadosMedico
 public DadosMedico(Medico medico) {
     // Utiliza o construtor primário do record para inicializar os campos com base no objeto Medico fornecido
     this( medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
 
 }
}