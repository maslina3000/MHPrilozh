﻿using System;
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
        }
        string ColorPick;
        string LightPick;
        string FrequencyPick;
        string VibroPick;

        private void button1_Click(object sender, EventArgs e)
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

        private void button2_Click(object sender, EventArgs e)
        {
            ColorPick = "#ff0000";
        }

        private void button3_Click(object sender, EventArgs e)
        {
            ColorPick = "#ff8000";
        }

        private void button4_Click(object sender, EventArgs e)
        {
            ColorPick = "#ffff00"; 
        }

        private void button5_Click(object sender, EventArgs e)
        {
            ColorPick = "#00ff00";
        }

        private void button6_Click(object sender, EventArgs e)
        {
            ColorPick = "#00ffff";
        }

        private void button7_Click(object sender, EventArgs e)
        {
            ColorPick = "#ff00ff";
        }
    }
}