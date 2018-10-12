package polirats.ratstats;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;
import static polirats.ratstats.NovaPartida.adversario;
import static polirats.ratstats.NovaPartida.campeonato;
import static polirats.ratstats.NovaPartida.dataPartida;
import static polirats.ratstats.NovaPartida.idPartida;

public class JogadaAtaque {

	//Cada jogada é salva em um .txt no formato csv com as variaveis na ordem em que aparecem aqui
	//public static String idPartida, dataPartida, adversario, campeonato;
	private String idJogada, horaJogada, nomeFalta, unidadeFalta, drive;
	private int down, distance, scrimmage, jdsConquistadas, jdsFalta;
	private int half, pontRats, pontAdv;
	private boolean corrida, passe, completo, incompleto, drop, interceptado, sack, badSnap, TD, safety, falta, twoPointTry, twoPointGood;
	private Jogador[] emCampo;
	private Jogador runner, quarterback, target, fazedor, tomador;


	public static int nJogadas;

	/*
	idPartida + "," + dataPartida + "," + adversario + "," + campeonato + "," + this.idJogada + "," + this.horaJogada +

	"," + Integer.toString(this.down) + "," + Integer.toString(this.distance) + "," + Integer.toString(this.scrimmage) +
	"," + Integer.toString(this.jdsConquistadas) + "," + Integer.toString(this.half) + "," + Integer.toString(this.pontRats) +
	"," + Integer.toString(this.pontAdv) +

	"," + Boolean.toString(this.corrida) + "," + runnerApelido +
	"," + Boolean.toString(this.passe) + "," + Boolean.toString(this.completo) + "," + Boolean.toString(this.incompleto) +
	"," + Boolean.toString(this.drop) + "," + Boolean.toString(this.interceptado) + "," + quarterbackApelido + "," + targetApelido +
	"," + Boolean.toString(this.sack) + "," + Boolean.toString(this.badSnap) +

	"," + Boolean.toString(this.TD) + "," + Boolean.toString(this.safety) + "," + Boolean.toString(this.falta) +
	"," + this.nomeFalta + "," + this.unidadeFalta + "," + Integer.toString(this.jdsFalta) + fazedorApelido + "," + tomadorApelido +

	"," + emCampo[0].getApelido() + "," + emCampo[1].getApelido() + "," + emCampo[2].getApelido() + "," + emCampo[3].getApelido() +
	"," + emCampo[4].getApelido() + "," + emCampo[5].getApelido() + "," + emCampo[6].getApelido() + "," + emCampo[7].getApelido() +
	"\n";
	 */


	public JogadaAtaque(String drive, int down, int distance, int scrimmage, int jdsConquistadas, int half, int pontRats, int pontAdv,
						boolean corrida, Jogador runner, boolean passe, boolean completo, boolean incompleto, boolean drop,
						boolean interceptado, Jogador quarterback, Jogador target, boolean sack, boolean badSnap,
						boolean TD, boolean safety, boolean twoPointTry, boolean twoPointGood, boolean falta, String nomeFalta, String unidadeFalta,
						int jdsFalta, Jogador fazedor, Jogador tomador, Jogador[] emCampo) {
		nJogadas++;
		this.drive = drive;
		this.idJogada = Integer.toString(nJogadas);
		this.horaJogada = getHora();
		this.down = down;
		this.distance = distance;
		this.scrimmage = scrimmage;
		this.jdsConquistadas = jdsConquistadas;
		this.half = half;
		this.pontAdv = pontAdv;
		this.pontRats = pontRats;
		this.TD = TD;
		this.safety = safety;
		this.corrida = corrida;
		this.passe = passe;
		this.completo = completo;
		this.incompleto = incompleto;
		this.drop = drop;
		this.interceptado = interceptado;
		this.sack = sack;
		this.badSnap = badSnap;
		this.falta = falta;
		this.target = target;
		this.runner = runner;
		this.fazedor = fazedor;
		this.tomador = tomador;
		this.quarterback = quarterback;
		this.emCampo = new Jogador[8];
		this.emCampo = emCampo;
		this.nomeFalta = nomeFalta;
		this.jdsFalta = jdsFalta;
		this.unidadeFalta = unidadeFalta;
		this.twoPointGood = twoPointGood;
		this.twoPointTry = twoPointTry;


		if (jdsFalta == 0)
			GameSituation.updateDownDistance(jdsConquistadas);
		else
			GameSituation.updateFalta(jdsFalta);
		if (TD)
			if (interceptado)
				GameSituation.updateScore(6, "adv");
			else
				GameSituation.updateScore(6, "rats");
		else if (safety)
			GameSituation.updateScore(2, "adv");
		else if (twoPointGood)
			GameSituation.updateScore(2, "rats");

		//this.append(jogadas.txt);

	}

	public String getHora() {
		DateFormat timeOnly = new SimpleDateFormat("HH:mm:ss");
		return timeOnly.format(Calendar.getInstance().getTime());
	}

	public String getCsvText() {

		String targetApelido, runnerApelido, quarterbackApelido, fazedorApelido, tomadorApelido;
		if (this.target == null)
			targetApelido = "na";
		else
			targetApelido = this.target.getApelido();

		if (this.runner == null)
			runnerApelido = "na";
		else
			runnerApelido = this.runner.getApelido();

		if (this.quarterback == null)
			quarterbackApelido = "na";
		else
			quarterbackApelido = this.quarterback.getApelido();

		if (this.fazedor == null)
			fazedorApelido = "na";
		else
			fazedorApelido = this.fazedor.getApelido();

		if (tomador == null)
			tomadorApelido = "na";
		else
			tomadorApelido = this.tomador.getApelido();


		return idPartida + "," + dataPartida + "," + adversario + "," + campeonato + "," + this.idJogada + "," + this.horaJogada + "," + this.drive +
				"," + Integer.toString(this.down) + "," + Integer.toString(this.distance) + "," + Integer.toString(this.scrimmage) +
				"," + Integer.toString(this.jdsConquistadas) + "," + Integer.toString(this.half) + "," + Integer.toString(this.pontRats) +
				"," + Integer.toString(this.pontAdv) +

				"," + Boolean.toString(this.corrida) + "," + runnerApelido +
				"," + Boolean.toString(this.passe) + "," + Boolean.toString(this.completo) + "," + Boolean.toString(this.incompleto) +
				"," + Boolean.toString(this.drop) + "," + Boolean.toString(this.interceptado) + "," + quarterbackApelido + "," + targetApelido +
				"," + Boolean.toString(this.sack) + "," + Boolean.toString(this.badSnap) +

				"," + Boolean.toString(this.TD) + "," + Boolean.toString(this.safety) + "," + Boolean.toString(this.twoPointTry) + "," + Boolean.toString(twoPointGood) +
				"," + Boolean.toString(this.falta) + "," + this.nomeFalta + "," + this.unidadeFalta + "," + Integer.toString(this.jdsFalta) + "," + fazedorApelido + "," + tomadorApelido +

				"," + emCampo[0].getApelido() + "," + emCampo[1].getApelido() + "," + emCampo[2].getApelido() + "," + emCampo[3].getApelido() +
				"," + emCampo[4].getApelido() + "," + emCampo[5].getApelido() + "," + emCampo[6].getApelido() + "," + emCampo[7].getApelido() +
				"\n";
	}

	public String getIdJogada() {
		return idJogada;
	}

	public void setIdJogada(String idJogada) {
		this.idJogada = idJogada;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getScrimmage() {
		return scrimmage;
	}

	public void setScrimmage(int scrimmage) {
		this.scrimmage = scrimmage;
	}

	public int getJdsConquistadas() {
		return jdsConquistadas;
	}

	public void setJdsConquistadas(int jdsConquistadas) {
		this.jdsConquistadas = jdsConquistadas;
	}

	public int getHalf() {
		return half;
	}

	public void setHalf(int half) {
		this.half = half;
	}

	public int getPontRats() {
		return pontRats;
	}

	public void setPontRats(int pontRats) {
		this.pontRats = pontRats;
	}

	public int getPontAdv() {
		return pontAdv;
	}

	public void setPontAdv(int pontAdv) {
		this.pontAdv = pontAdv;
	}

	public boolean isCorrida() {
		return corrida;
	}

	public void setCorrida(boolean corrida) {
		this.corrida = corrida;
	}

	public boolean isPasse() {
		return passe;
	}

	public void setPasse(boolean passe) {
		this.passe = passe;
	}

	public boolean isCompleto() {
		return completo;
	}

	public void setCompleto(boolean completo) {
		this.completo = completo;
	}

	public boolean isIncompleto() {
		return incompleto;
	}

	public void setIncompleto(boolean incompleto) {
		this.incompleto = incompleto;
	}

	public boolean isInterceptado() {
		return interceptado;
	}

	public void setInterceptado(boolean interceptado) {
		this.interceptado = interceptado;
	}

	public boolean isSack() {
		return sack;
	}

	public void setSack(boolean sack) {
		this.sack = sack;
	}

	public boolean isBadSnap() {
		return badSnap;
	}

	public void setBadSnap(boolean badSnap) {
		this.badSnap = badSnap;
	}

	public boolean isTD() {
		return TD;
	}

	public void setTD(boolean TD) {
		this.TD = TD;
	}

	public boolean isSafety() {
		return safety;
	}

	public void setSafety(boolean safety) {
		this.safety = safety;
	}

	public Jogador getTarget() {
		return target;
	}

	public void setTarget(Jogador target) {
		this.target = target;
	}

	public Jogador getRunner() {
		return runner;
	}

	public void setRunner(Jogador runner) {
		this.runner = runner;
	}

	public Jogador getQuarterback() {
		return quarterback;
	}

	public void setQuarterback(Jogador quarterback) {
		this.quarterback = quarterback;
	}

	/*public Jogador[] getEmCampo() {
		return emCampo;
	}

	public void setEmCampo(Jogador[] emCampo) {
		this.emCampo = emCampo;
	}*/

	public static int getnJogadas() {
		return nJogadas;
	}

	public static void setnJogadas(int nJogadas) {
		JogadaAtaque.nJogadas = nJogadas;
	}

	public Jogador getFazedor() {
		return fazedor;
	}

	public void setFazedor(Jogador fazedor) {
		this.fazedor = fazedor;
	}

	public Jogador getTomador() {
		return tomador;
	}

	public void setTomador(Jogador tomador) {
		this.tomador = tomador;
	}
}