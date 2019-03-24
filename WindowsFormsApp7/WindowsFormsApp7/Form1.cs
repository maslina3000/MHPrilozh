using System;
using System.Collections.Generic;
using System.IO;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Newtonsoft.Json;
using System.Web.Script.Serialization;



namespace WindowsFormsApp7
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            comboBox1.SelectedIndex = 0;
        }
        string ColorPick;
        string LightPick;
        string FrequencyPick;
        string VibroPick;
        string Msg;

        private void SendColour()
        {
            var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://178.249.243.15:3313/setScreen");
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Method = "POST";

            using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
            {
                string json = new JavaScriptSerializer().Serialize(new
                {

                    color = ColorPick,
                    light = "10",
                    frequency = "10",
                    vibro = "true"
                });

                streamWriter.Write(json);


            }

            var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
            using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
            {
                var result = streamReader.ReadToEnd();
                label1.Text = Convert.ToString(result);
            }
        }
            private void SendMsg()
            {
                var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://178.249.243.15:3313/pushMsg");
                httpWebRequest.ContentType = "application/json";
                httpWebRequest.Method = "POST";

                using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
                {
                    string json = new JavaScriptSerializer().Serialize(new
                    {



                         from = "id",
                         toAll = "true",
                         comand = comboBox1.Text,
                         msg = Msg
                    });

                    streamWriter.Write(json);


                }

                var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
                using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
                {
                    var result = streamReader.ReadToEnd();
                    label1.Text = Convert.ToString(result);
                }
            }

        

        private void button1_Click(object sender, EventArgs e)
        {
            SendColour();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            ColorPick = "#ff0000";
            SendColour();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            ColorPick = "#ff8000";
            SendColour();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            ColorPick = "#ffff00";
            SendColour();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            ColorPick = "#00ff00";
            SendColour();
        }

        private void button6_Click(object sender, EventArgs e)
        {
            ColorPick = "#00ffff";
            SendColour();
        }

        private void button7_Click(object sender, EventArgs e)
        {
            ColorPick = "#ff00ff";
            SendColour();
        }

        private void button8_Click(object sender, EventArgs e)
        {
            ColorPick = "#c0c0c0";
            SendColour();
        }

        private void SendMessage_Click(object sender, EventArgs e)
        {
            Msg = textBox1.Text;
            SendMsg();
        }
    }
}
