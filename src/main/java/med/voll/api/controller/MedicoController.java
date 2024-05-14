package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosDetalhamentoMedico;
import med.voll.api.medico.DadosMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.medico.atualizarDadosCadastroMedico;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	@Autowired
	private final MedicoRepository medicoRepository;

	public MedicoController(MedicoRepository medicoRepository) {
		this.medicoRepository = medicoRepository;
	}

	@PostMapping
	public ResponseEntity<URI> cadastrar(@RequestBody @Valid DadosCadastroMedico dados,
			UriComponentsBuilder uriBuilder) {
		// cria uma instancia da classe Médico a partir dos dados fornecidos
		var medico = new Medico(dados);
		// Salva o médico no banco de dados através do repositório
		medicoRepository.save(medico);
		// cria o URI para o recurso do médico recém-criado
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

		// salva o objeto médico no banco de dados por meio do repository

		return ResponseEntity.created(uri).body(uri);

	}

	@GetMapping
	// Como fica a url em caso de consulta deste get :
	// http://localhost:8080/medicos?size=2&sort=nome,desc
	// O método retorna uma lista de objetos DadosMedico
	public ResponseEntity<Page<DadosMedico>> listarMedicos(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosMedico::new);
		return ResponseEntity.ok(page);

	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoMedico> atualizarCadastroMedico(
			@RequestBody @Valid atualizarDadosCadastroMedico dados) {

		// Obtém uma referência ao médico pelo ID fornecido nos dados
		var medico = medicoRepository.getReferenceById(dados.id());

		// Atualiza as informações do médico com base nos dados fornecidos
		medico.atualizarInformacoes(dados);
		System.out.println("Médico Atualizado com sucesso: " + medico);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

		// Exibe uma mensagem informando que o médico foi atualizado com sucesso
	}

	@DeleteMapping("/{id}") // aqui notificamos que via url o id será informado para delertarmos os dados
	@Transactional
	public ResponseEntity<Medico> excluirMedico(@PathVariable Long id) {

		var medico = medicoRepository.getReferenceById(id);
		medico.excluir();

		return ResponseEntity.noContent().build();

	}

}
/////////////////////////////////////////////////////////// SOBRE POST ///////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////// SOBRE GET ////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////// SOBRE PUT ////////////////////////////////////////////////////////////////////

/**
 * Método para atualizar o cadastro de um médico.
 * 
 * @param dados Objeto contendo os dados atualizados do cadastro do médico.
 */

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
 * medicoRepository.getReferenceById(dados.id()): Obtém uma referência ao médico
 * no repositório com base no ID fornecido nos dados de atualização.
 * medico.atualizarInformacoes(dados): Atualiza as informações do médico com os
 * dados fornecidos. System.out.println("Médico Atualizado com sucesso: " +
 * medico): Exibe uma mensagem no console informando que o médico foi atualizado
 * com sucesso.
 * 
 */

/////////////////////////////////////////////////////////// SOBRE DELETE /////////////////////////////////////////////////////////////////

/*
 * método para excluir um registro totalmente do banco de dados // forma que não
 * se deve utilizar
 * 
 * @DeleteMapping("/{id}") // aqui notificamos que via url o id será informado
 * para delertarmos os dados
 * 
 */

/////////////////////////////////////////////////////////// COMENTÁRIOS AVULSOS //////////////////////////////////////////////////////////////////////////

// definiti que a classe é um controlador REST DO SPRING
// DEFINIR A RAIZ DO MAPEAMENTO DE URL PARA ESTE CONTROLADOR

// É necessário o SERVICE(REPOSITORY)para existir comunicação entre o banco de dados ( persistencia) e o controller.

// as requisições partem do controller e porisso é o ponto de partida de um projeto.