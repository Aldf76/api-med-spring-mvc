package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.atualizarDadosCadastroMedico;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import med.voll.api.paciente.atualizarDadosCadastroPaciente;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	@Autowired
	private final PacienteRepository pacienteRepository;

	public PacienteController(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}

	@PostMapping
	public void cadastroPaciente(@RequestBody @Valid DadosCadastroPaciente dados) {
		// cria uma instancia da classe Médico a partir dos dados fornecidos
		var paciente = new Paciente(dados);
		// salva o objeto médico no banco de dados por meio do repository
		pacienteRepository.save(paciente);
		System.out.println("Paciente cadastrado com sucesso" + paciente);

	}

	@GetMapping
	// Como fica a url em caso de consulta deste get :
	// http://localhost:8080/medicos?size=2&sort=nome,desc
	// O método retorna uma lista de objetos DadosMedico
	public Page<DadosPaciente> listarPacientes(
			@PageableDefault(page = 0, size = 10, sort = { "nome" }) Pageable paginacao) {
		return pacienteRepository.findAll(paginacao).map(DadosPaciente::new);

	}

	@PutMapping
	@Transactional
	public void atualizarCadastroPaciente(@RequestBody @Valid atualizarDadosCadastroPaciente dados) {

		// Obtém uma referência ao médico pelo ID fornecido nos dados
		var paciente = pacienteRepository.getReferenceById(dados.id());

		// Atualiza as informações do médico com base nos dados fornecidos
		paciente.atualizarInformacoes(dados);

		// Exibe uma mensagem informando que o médico foi atualizado com sucesso
		System.out.println("Médico Atualizado com sucesso: " + paciente);

		/*
		 * Neste método:
		 * 
		 * @PutMapping: Especifica que este método será acionado quando uma requisição
		 * PUT for feita para o endpoint associado.
		 * 
		 * @Transactional: Indica que a transação será gerenciada automaticamente pelo
		 * Spring, garantindo atomicidade das operações do banco de dados.
		 * 
		 * @RequestBody @Valid: Indica que os dados da requisição serão mapeados para o
		 * objeto AtualizarDadosCadastroMedico e validados.
		 * 
		 * medicoRepository.getReferenceById(dados.id()): Obtém uma referência ao médico
		 * no repositório com base no ID fornecido nos dados de atualização.
		 * 
		 * paciente.atualizarInformacoes(dados): Atualiza as informações do médico com os
		 * dados fornecidos.
		 * 
		 * System.out.println("Médico Atualizado com sucesso: " +
		 * medico): Exibe uma mensagem no console informando que o médico foi atualizado
		 * com sucesso.
		 * 
		 */
		
	/* Para criar este método :
	 * - irei criar um método record que irá interagir somente com atributos selecionados e passíveis de edição  : atualizarDadosCadastroPaciente
	 * - irei criar um método de validação ínfima no modelo para os dados que o usuário irá lançar ao sistema    : atualizarInformacoes
	 * - 
	 */
		

	}
	
	
	
	
}


// É necessário o SERVICE(REPOSITORY)para existir comunicação entre o banco de dados ( persistencia) e o controller.

// as requisições partem do controller e por isso é o ponto de partida de um projeto.