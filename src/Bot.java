import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Bot {

    private String name = "name";
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
	private Channel = "#channel";
	private Server = "irc.freenode.org";
	private Port = 6667;
	private User = "user"
	private RealName = "real name"
	
    public static void main(String args[])
    {
        try {
            new Bot().start();

        }
        catch (java.io.IOException e) {
        }
    }

    void start() throws java.io.IOException
    {
        this.socket = new Socket(Server,Port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        if (socket.isConnected())
        {
            out.write("NICK " + name + "\n");
            out.write("USER " + User + "* 8 " + RealName + "\n");
            out.write("JOIN " + Channel + "\n");
            out.flush();
        }

        while (socket.isConnected())
        {
            String buffer;
            try
            {
                while ((buffer = in.readLine()) != null)
                {
                    System.out.println(buffer);
                    if (buffer.startsWith("PING"))
                    {
                        out.write("PONG " + buffer.substring(5) + "\r\n");
                        out.flush();
                    }
                    int k;
                    if ((k = buffer.indexOf("PRIVMSG " + Channel != -1)
                    {
                        String message = buffer.substring(k+17);
                        System.out.println("message: " + message);
                        if ((message.indexOf(name))!=-1)
                        {
                            if ((message.toLowerCase().indexOf("show"))!=-1)
                            {
                                if ((k=message.toLowerCase().indexOf("some"))!=-1)
                                {
                                    String info = message.substring(k+5);
                                    int j = info.indexOf(' ');

                                    if (j!=-1 && info.toLowerCase().startsWith("trail"))
                                    {

                                        int q = info.substring(j).indexOf(' ',j);
                                        j += q;
                                    }
                                    String name;
                                    if (j>0)
                                    {
                                        name = info.substring(0,j).toLowerCase();
                                    }
                                    else
                                    {
                                        name = info.toLowerCase();
                                    }

                                    Api api = new Api();
                                    String[] result;
                                    try {
                                        result = api.teaminfo(name);
                                        for (int i=0; i<4; i++)
                                            out.write("PRIVMSG " + Channel + " :" + result[i] + "\n");
                                        out.flush();
                                    }
                                    catch (Exception e) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch (java.io.IOException e){
            }
        }
    }
}
