import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client
{

        public static void main(String[] args)
        {
            System.out.println("Hello !");

            Socket socket = null;
            InputStreamReader inputStreamReader = null;
            OutputStreamWriter outputStreamWriter = null;
            BufferedReader bufferedReader = null;
            BufferedWriter bufferedWriter = null;
            try
            {
                socket = new Socket("localhost", 12345);
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                Scanner scanner=new Scanner(System.in);
                while(true)
                {
                    String input = scanner.nextLine();
                    bufferedWriter.write(input);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    System.out.println("Server:" + bufferedReader.readLine());
                    if (input.equalsIgnoreCase("BYE")) break;

                }



            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            finally{
                try{
                    if(socket!=null) socket.close();
                    if(inputStreamReader!=null) inputStreamReader.close();
                    if(outputStreamWriter!=null) outputStreamWriter.close();
                    if(bufferedReader!=null) bufferedReader.close();
                    if(bufferedWriter!=null) bufferedWriter.close();
                }
                catch (IOException e){
//                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

            }


        }


}

