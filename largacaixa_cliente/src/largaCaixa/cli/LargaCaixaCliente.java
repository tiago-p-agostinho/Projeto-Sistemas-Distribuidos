package largaCaixa.cli;

import pt.largacaixa.ws.*; // classes generated from WSDL

import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;

import javax.xml.ws.*;
import javax.imageio.ImageIO;
import javax.annotation.PostConstruct;
import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;

/**
*	Classe LargaCaixaCliente
*		Permite a transferência de ficheiros do cliente para o servidor e do servidor para o cliente
*		Implementa uma consola para a interaccao do cliente
*
*/
public class LargaCaixaCliente {
    
	private String endpointAddress; 
	private LargaCaixaPortType lc;
	public String newEndpoint;
	private Consola _consola;
	public Query query;
	public Map<String, Object> requestContext;
	
	/**
	*	Construtor do cliente que trata da ligacao com o servidor
	*
	*/
	public LargaCaixaCliente() throws Exception{
		
		//binding + port
		LargaCaixaService service = new LargaCaixaService();
			
		LargaCaixaPortType port = service.getLargaCaixaPort();
        
	    query = new Query();
		endpointAddress = query.search();
		
		
		//get endpoint adress
		BindingProvider bindingProvider = (BindingProvider) port;
		   
	    requestContext = bindingProvider.getRequestContext();
			
		System.out.println("Web Service endpoint address:");
		 // retrieve old address
        String oldEndpointAddress = (String) requestContext.get(ENDPOINT_ADDRESS_PROPERTY);	
		System.out.println(endpointAddress);
		
		// set endpoint address
        requestContext.put(ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
		
		lc = port;
	
		_consola = new Consola();
		
		String[] commandVector= {"add Tolkien mariazinha 10",
								"add Pessoa zeninguem 0",
								"add Camoes zeninguem 0",
								"add Tecnico ist 0",
								"add ISTory ist 5"};
		for (String s: commandVector){						
			_consola.lerConsola(s);
			_consola.usarComando();
		}
	}

	public Consola getConsola(){
		return _consola;
	}
	public class Consola{
	
		private String newLine = System.getProperty("line.separator");
	
		private List<Object> _compList = new ArrayList<Object>();
	
		private String[] lastCommand=null;
		
		/**
		*	Usa o comando passado pelo cliente apos a sua validacao, verifica o primeiro argumento do comando lastCommand[0] 
		*	e trata da comunicacao com o servidor, com os outros comandos. A seguinte lista explica os argumentos passados e 
		*	esta' acessivel ao cliente através do comando "help":
		*
		*		help                 - mostra comandos
		*		add cont ca p        - adiciona ficheiro com nome-cont a caixa-ca com preco-p
		*		apaga cont ca        - apaga conteudo-cont da caixa-ca
		*		lista ca             - lista conteudos da caixa-ca
		*		obter cont ca        - retira os conteudos-cont da caixa-ca
		*		share cont ca_1 ca_2 - partilha os conteudos-cont da caixa-ca_1 com
		*		                       a caixa -ca_2(verifica se foi adicionado comprovativo)
		*		comprov cont ca p	 - permite ao utilizador criar um comprovativo artificial para testes
		*		exit                 - termina programa
		*
		*/
		public void usarComando() 
			throws UnsupportedEncodingException, IOException{
				if (lastCommand[0].equals("help")){
				
					comandoHelp();
					
				}else if (lastCommand[0].equals("add")){ 
				
					comandoAdd();
					
				}else if (lastCommand[0].equals("apaga")){	
				
					comandoApaga();
					
				}else if (lastCommand[0].equals("lista")){	
				
					comandoLista();
					
				}else if (lastCommand[0].equals("obter")){
				
					comandoObter();
					
				}else if (lastCommand[0].equals("share")){	
				
					comandoShare();
					
				}else if(lastCommand[0].equals("comprov")){
				
					comandoComprov();
					
				}
		}
		
		public void comandoHelp(){
			System.out.println(
							"help                 - mostra comandos\n"+
							"add cont ca p        - adiciona ficheiro com nome-cont a caixa-ca com preco-p\n"+
							"apaga cont ca        - apaga conteudo-cont da caixa-ca\n"+
							"lista ca             - lista conteudos da caixa-ca\n"+
							"obter cont ca        - retira os conteudos-cont da caixa-ca\n"+
							"share cont ca_1 ca_2 - partilha os conteudos-cont da caixa-ca_1 com\n"+
							"                       a caixa -ca_2(verifica se foi adicionado comprovativo)\n"+
							"comprov cont  		  - permite ao utilizador criar um comprovativo simplificado com id de conteudo -cont\n"+
							"exit                 - termina programa"
						);
		}
			public void comandoAdd() throws IOException{
				try{
					//Verifica se o nome de ficheiro passado pelo utilizador tem extensao ou nao
					String ficheiro = lastCommand[1];
					if (!existeExtensao(lastCommand[1]))
						ficheiro = lastCommand[1]+"."+extensaoFicheiro(lastCommand[1]);
					lc.criarConteudo(ficheiro,lastCommand[2],Integer.parseInt(lastCommand[3]),
														verificaExtensao(ficheiro).equals("txt")?lerFicheiroTXT(ficheiro):
															verificaExtensao(ficheiro).equals("png")?lerFicheiroPNG(ficheiro):null);
				}catch(ConteudoInvalido eInv){
					System.out.println("[Console] Conteudo invalido.");
				}catch(CaixaInexistente eInex){
					System.out.println("[Console] A caixa com o nome "+lastCommand[2]+" nao existe.");
				}
			}
		
		public void comandoApaga(){
			try{
				lc.apagarConteudo(lastCommand[1],lastCommand[2]);
			}catch(CaixaInexistente eInex){
				System.out.println("[Console] A caixa com o nome "+lastCommand[2]+" nao existe.");
				return;
			}catch (ConteudoInexistenteNaCaixa eContInex){
				System.out.println("[Console] O conteudo "+lastCommand[1]+" nao existe na caixa "+lastCommand[2]+".");
			}
		}
		
		public void comandoLista(){
			try{
				mostraListaConteudos(lc.listarConteudos(lastCommand[1]));
				return;
			}catch(CaixaInexistente eInex){
				System.out.println("[Console] A caixa com o nome "+lastCommand[1]+" nao existe.");
				return;
			}	
	}
		
		
		public void comandoObter() throws IOException{
			try{
				byte[] array = lc.obterConteudo(lastCommand[1],lastCommand[2]);
				File f = new File(lastCommand[2]);
				f.mkdir();
				guardaFicheiroLocalmente(lastCommand[1],lastCommand[2],f,array);
				System.out.println("Ficheiro gravado em: "+f.getAbsolutePath());
			}catch(CaixaInexistente eInex){
				System.out.println("[Console] A caixa "+lastCommand[2]+" nao existe.");
			}catch (ConteudoInexistenteNaCaixa eContInex){
				System.out.println("[Console] O conteudo "+lastCommand[1]+" nao existe na caixa "+lastCommand[2]+".");
			}
		}
		
		
		
		public void comandoShare(){
			try{ 
				Object comprovativoCifrado= Crypto.cipher("priv.key", "pub.key", getComprovativo(lastCommand[1]));
				lc.partilharConteudo(lastCommand[1],lastCommand[2],lastCommand[3],
						 comprovativoCifrado);
			}catch (CaixaInexistente eInex){
				System.out.println("[Console] Uma das caixas nao existe.");
			}catch (ConteudoInexistenteNaCaixa cContInex){
				System.out.println("[Console] O conteudo "+lastCommand[1]+" nao existe na caixa "+lastCommand[2]+".");
			}catch (ComprovativoRejeitado eRej){
				System.err.println("[Consolo] O comprovativo foi rejeitado. " );
			}
			catch(Exception e){
				System.err.println("[Consolo]O comprovativo foi rejeitado. ");
			}
		}
		
		public void comandoComprov(){
			_compList.add(lastCommand[1]);
		}
		
		/**
		*	Lê o comando passado pelo cliente na consola, dividindo-o nos seus argumentos fazendo as
		*	verificacoes necessarias para assegurar a validade dos argumentos passados, guarda o ultimo 
		*	comando internamente.
		*
		*	@param s comando passado pelo cliente
		*	@return o primeiro argumento do comando passado para verificar se e' "exit" ou "invalid" ou
		*				a string "invalid" caso nao tenha passado as verificacoes (a unica razao de ser retornada
		*				esta String e' por ser diferente de "exit")
		*/
		public String lerConsola(String s){
			lastCommand = s.split("\\s");
			if (verValidade(lastCommand))
				return lastCommand[0];
				
			System.out.println("[Console] O comando não foi entendido.");
			lastCommand = null;
			return "invalid";
			
		}
		
		
	   /**
		*	Retorna um array de bytes de um determinado ficheiro local para poder ser transferido 
		*	para o servidor. Primeiro verifica se ficheiro é de texto (.txt), caso não seja lanca 
		*	uma exepção e verifica se o ficheiro é uma imagem (.png). Caso não seja nenhum, determina
		*	que não existe (retorna null).
		*
		*	@param nome ficheiro a ser lido
		*	@return a conversao para byte array ou nulo se nao for encontrado
		*
		*	@throws IOException
		*	@throws UnsupportedEncodingException	
		*/
		public byte[] lerFicheiroTXT(String nome) throws IOException, UnsupportedEncodingException{
			String line,str = "";
			try{
				BufferedReader in = new BufferedReader(new FileReader(nome));
				while ((line=in.readLine())!=null)
					str+=line+newLine;
				in.close();
				return str.getBytes("UTF-8");
			} catch(FileNotFoundException e){
				System.out.println("[Console] File not found: "+nome);
				return null;
			}
		
		}
		/**
		*	Verifica para um ficheiro passado pelo cliente que não contenha extensao, qual a sua extensao
		*
		*	@param nome ficheiro sem extensao
		*	@return extensao do ficheiro 
		*/
		public String extensaoFicheiro(String nome) throws IOException{
				 
				File f = new File(nome+".txt");
				if(f.exists())
					return "txt";
					
				f = new File(nome+".png");
				if (f.exists())
					return "png";
				return null;
		}
		/**	
		*	Retira a extensao do nome completo de um ficheiro e retorna-o
		*	
		*	@param nome	nome do ficheiro com extensao
		*	@return extensao do ficheiro
		*/
		public String verificaExtensao(String nome){
			String[] s = nome.split("\\.");
			return s[1];
		}
		/**	
		*	Retira o nome de um ficheiro com a sua extensao e retorna-o
		*	
		*	@param nome	nome do ficheiro com extensao
		*	@return nome do ficheiro
		*/
		public String verificaNome(String nome){
			String[] s = nome.split("\\.");
			return s[0];
		}
		/**
		*	Verifica se um dado nome contem a extensao
		*	Nota:  Este método só verifica para nomes de ficheiro com 1 ponto (.) 
		*
		*	@param nome
		*	@return verifica se existe extensao
		*/
		public boolean existeExtensao(String nome){
				return (nome.split("\\.").length > 1);
		}
		
		/**
		*	Quando o cliente quer obter um ficheiro, mas não diz qual e' a extensao do ficheiro,	
		*	O cliente procura pelo ficheiro na lista da caixa (do lado do servidor)
		*
		*	@param cid identificacao do conteudo que nao tem extensao associada
		*	@param caixa caixa onde esta' o ficheiro
		*	@return extensao do ficheiro (txt/png)
		*/
		public String procuraExtensaoServidor(String cid, String caixa) throws CaixaInexistente{
			List<String> cont = lc.listarConteudos(caixa);
			for (String s:cont)
				if (verificaNome(s).equals(cid))
					return verificaExtensao(s);
			return null;
		}
		/**
		*	Permite que o array de bytes possa ser guardado na máquina local
		*	
		*	@param nome conteudo que se procurou no servidor
		*	@param caixa caixa de onde se retirou o conteudo
		*	@param f	pasta onde se vai guardar o conteudo
		*	@param array bytes que vao ser transformados
		*
		*	@throws IOException
		*/
		public void guardaFicheiroLocalmente(String nome,String caixa, File f,byte[] array) 
			throws IOException, CaixaInexistente{
			
			//Caso o utilizador tenha inserido a extensao, isto retira-lhe porque o programa da' a extensao automaticamente
			if(existeExtensao(nome))
				nome = verificaNome(nome);
			
			String ext = procuraExtensaoServidor(nome,caixa);	
				
			if (ext.equals("txt")){
				Writer out = new BufferedWriter(new FileWriter(new File(f,nome+".txt")));
				out.write(new String (array,"UTF-8"));
				out.close();
			}else{
				BufferedImage bi = ImageIO.read(new ByteArrayInputStream(array));
				ImageIO.write(bi,"png",new File(f,nome+".png"));
			}
		}
		
		
		/**
		*	Permite que um ficheiro do tipo PNG possa ser lido a partir de uma caixa e 
		*	transmitido para o servidor como array de bytes
		*
		*	@param nome ficheiro que ser quer guardar
		*	@return array de bytes correspondente à informacao retirada do ficheiro
		*
		*	@throws IOException
		*/
		public byte[] lerFicheiroPNG(String nome) throws IOException{
			try{
				BufferedImage bi = ImageIO.read(new File(nome));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bi,"png",baos);
				byte[] array = baos.toByteArray();
				baos.close();
				return array;
			}catch(FileNotFoundException e){
				System.out.println("[Console] File not found: "+nome);
				return null;
			}
		
		}
		
		/**
		*	Verifica se o último comando submetido pelo utilizador foi invalidado pela consola
		*	
		*	@return verificacao de ultimo comando ser nulo
		*/
		public boolean comandoInvalidado(){
			return lastCommand == null;
		}
		
		/**
		*	Mostra na consola a lista de conteudos de uma caixa dada pelo servidor
		*
		*	@param cid lista de conteudos
		*/
		public void mostraListaConteudos(List<String> cid){
			for (String s: cid)
				System.out.println(s);
		}
		/**
		*	Encontra o comprovativo(String) adequado na lista de comprovativos 
		*
		*	@param cid	conteudo comprado por este cliente
		*	@return comprovativo 
		*/
		public Object getComprovativo(String cid){
			for (Object s: _compList)
				if ((s.toString()).equals(cid))
					return s;
			return null;
		}
	
		/**
		*	Verifica se as comandos dados pelo utilizador existem e se tem o tamanho certo
		*
		*	@param s vector que contem todos os argumentos passados pelo cliente
		*/
		public boolean verValidade(String[] s){
			if (s[0].equals("add") || s[0].equals("share"))
				return s.length==4;
			if (s[0].equals("apaga") || s[0].equals("obter"))
				return s.length==3;
			if (s[0].equals("lista") || s[0].equals("comprov"))
				return s.length==2;
			return s[0].equals("help") || s[0].equals("exit");
		}
	
	
	}
	
	public static void main(String[] args) throws Exception{
		LargaCaixaCliente caixa = new LargaCaixaCliente();
		Consola c = caixa.getConsola();
		Scanner sc = new Scanner(System.in);
		while (!c.lerConsola(sc.nextLine()).equals("exit")){
			if (c.comandoInvalidado())
				continue;
			try{	
			c.usarComando();
			}catch(Exception e){
			caixa.newEndpoint = caixa.query.search();
			caixa.requestContext.put(ENDPOINT_ADDRESS_PROPERTY, caixa.newEndpoint);
			System.out.println(caixa.newEndpoint);
		 }
		}
	}

}
