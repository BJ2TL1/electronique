#include <main.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char stream[10];
float thr = 20;

// Fonction pour allumer la LED rouge
void red() {
   output_a(0x02);
}
// Fonction pour allumer la LED verte
void green() {
   output_a(0x01);
}

// Fonction pour eteindre les LED
void clear() {
   output_a(0x00);
}
/*
 * Fonction pour le clignottement de la LED rouge
 */
void toggleRed() {
   int i = 0;
   while(i < 1) {
      red();
      delay_ms(50);
      clear();
      delay_ms(50);
      i++;
   }
}
/*
 * Fonction qui gere les interuptions et set le nouveau seuil critique
 */
#INT_RDA
void RDA_isr() {
   gets(stream);
   thr = atof(stream);
}

void main()
{
   enable_interrupts(INT_RDA);
   enable_interrupts(GLOBAL);
   setup_low_volt_detect(FALSE);
   setup_timer_1(T1_INTERNAL);
   setup_timer_0(RTCC_INTERNAL);
   
  float time = 0;
  float distance = 0;
  delay_ms(100);
   
   /******** demarrage du projet ********/
   int i = 0;
   for ( i=0;i<4;i++) { 
      green();
      delay_ms(100);
      red();
      delay_ms(100);
      clear();
   }
   output_low(PIN_B0);
   green();
   /*************************************/
   
   while(TRUE)   
   {
	  /******** train d'impulsion pour la sonde (trigger) ********/
	  output_high(PIN_B0); 
	  delay_us(20);
	  output_low(PIN_B0);
	  /***********************************************************/
	  
	  /********* gestion du retour d information (echo) *********/
	  while(!input(PIN_B4)); // Attende la reception d un echo
	  set_timer1(0);
	  while(input(PIN_B4)) ; // Attente de la fin de l echo
	  time = get_timer1();
	  /**********************************************************/
	  
	  /******** calcul de la distance ********/
	  distance = time * 0.00344;
	  char data[10];
	  itoa((int)distance,10,data);
	  puts(data);	// envoi de la distance vers l interface JAVA
	  /***************************************/
	  
	  /******** comparaison distance et seuil critique ********/
	  if (distance < thr) {
		 toggleRed();
	  }
	  else {
		 green();
	  }
	  delay_ms(1000);
	  /********************************************************/
   } 
}
   
