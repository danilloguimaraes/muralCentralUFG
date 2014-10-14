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



}
