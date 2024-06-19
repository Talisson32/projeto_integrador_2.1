package apoio;

import java.sql.*;
import java.io.*;
import java.util.*;


public class ConexaoBD {
    
    private static ConexaoBD instancia = null;
    private Connection conexao = null;
    
        public ConexaoBD(){
            try{//carrega informações do arquivo de propriedades
                Properties prop = new Properties(); //instanciado uma classe do tipo Properties, de nome prop(é uma classe que o Java traz já para carregar dados de arquivos externos para dentro do código)                
                prop.load(new FileInputStream("db.properties")); //aqui ele vai carregar o arquivo salvo de nome db.properties para usar os dados dentro dele:
                
                String dbdriver = prop.getProperty("db.driver"); //esses são os dados de dentro do db.properties
                String dburl = prop.getProperty("db.url");
                String dbuser = prop.getProperty("db.user");
                String dbsenha = "123";
                //carrega driver do BD
                Class.forName(dbdriver);
                
                if (dbuser.length() !=0){//conexão COM usuario e senha
                    conexao = DriverManager.getConnection(dburl, dbuser, dbsenha);
                    
                }
                else {//conexão SEM usuario e senha
                    conexao = DriverManager.getConnection(dburl);
                }
            }
            catch (Exception e){
                System.err.println(e);
            }
        }
    //retorna instancia
    public static ConexaoBD getInstance(){
        if(instancia == null){
            instancia = new ConexaoBD();
        }
        return instancia;
    }
    //retorna conexão
    public Connection getConnection(){
        if(conexao == null){
            throw new RuntimeException("conexao==null");
        }
        return conexao;
    }
    //efetua fechamento conexão
    public void shutDown(){
        try{
            conexao.close();
            instancia = null;
            conexao = null;
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
    
}
