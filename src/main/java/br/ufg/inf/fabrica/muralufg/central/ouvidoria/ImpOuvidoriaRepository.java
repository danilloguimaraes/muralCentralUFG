package br.ufg.inf.fabrica.muralufg.central.ouvidoria;

/**
 * Created by Paulo on 14/10/2014.
 */
public class ImpOuvidoriaRepository implements OuvidoriaRepository{

    public final String CONTEUDO = "Conteudo";
    public final String DATA = "Data";
    public final String FONTE = "Fonte";

    public static Datastore conectar(){

        Datastore datastore = null;
        try {
            // Setup the connection to Google Cloud Datastore and infer credentials
            // from the environment.
            datastore = DatastoreFactory.get().create(DatastoreHelper.getOptionsfromEnv()
                    .dataset(datasetId).build());
            return datastore;
        } catch (GeneralSecurityException exception) {
            System.err.println("Erro de segurança ao fazer a conexão com o banco de dados: " + exception.getMessage());
            System.exit(1);
        } catch (IOException exception) {
            System.err.println("Erro de E/S ao conectar com o banco de dados: " + exception.getMessage());
            System.exit(1);
        }

    }


    public static boolean insere(Assunto assunto){
        try {
            Entity.Builder entAssunto = Entity.newBuilder();
            entAssunto.setKey(makeKey());
            entAssunto.addProperty(makeProperty(CONTEUDO, makeValue(assunto.getConteudo())));
            entAssunto.addProperty(makeProperty(DATA, makeValue(assunto.getData())));
            entAssunto.addProperty(makeProperty(FONTE, makeValue(assunto.getFonte())));

            Mutation.Builder mutation = Mutation.newBuilder();
            mutation.addInsertAutoId(entAssunto);

            CommitRequest req = CommitRequest.newBuilder()
                    .setMutation(mutation)
                    .setMode(CommitRequest.Mode.NON_TRANSACTIONAL)
                    .build();

        }catch (DatastoreException exception){

            System.err.println("Erro ao realizar operações no banco de dados");
            // Log the exception, the name of the method called and the error code.
            System.err.println(String.format("DatastoreException(%s): %s %s",
                    exception.getMessage(),
                    exception.getMethodName(),
                    exception.getCode()));
            System.exit(1);
        }

    }



}
