package med.voll.api.paciente;

//Declaração da classe como um "record" do Java, uma forma concisa de criar classes imutáveis
public record DadosPaciente(String nome, String email, String Telefone, String cpf) {

 // Construtor adicional que aceita um objeto Paciente e cria um objeto DadosPaciente
 public DadosPaciente(Paciente Paciente) {
     // Utiliza o construtor primário do record para inicializar os campos com base no objeto Paciente fornecido
     this(Paciente.getNome(), Paciente.getEmail(), Paciente.getTelefone(), Paciente.getCpf());
 
 }
}