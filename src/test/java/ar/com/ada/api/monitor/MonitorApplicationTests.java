package ar.com.ada.api.monitor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.monitor.entities.Muestra;
import ar.com.ada.api.monitor.services.MuestraService;
import ar.com.ada.api.monitor.utils.ColoresUtils;
import ar.com.ada.api.monitor.utils.UbicacionesUtils;

@SpringBootTest
class MonitorApplicationTests {

	@Autowired
	MuestraService muestraService;
	// - Validar que las coordenadas esten dentro del rango del planeta
	// - Validar que las coordenadas NO esten dentro del rango del planeta
	// - Validar que las coordenadas esten en el hemisferio norte.

	@Test
	void testLatitudOk() {
		double latitudNoOk = -93;
		double latitudRegia = 15;

		assertTrue(UbicacionesUtils.validarLatitud(latitudRegia));
		assertFalse(UbicacionesUtils.validarLatitud(latitudNoOk));

		// Latitud: -90 + 90
		// Longitud: -180 +180
	}

	@Test
	void testLongitudOk() {
		double longitudNoOk = -186;
		double longitudRegia = 95;

		assertTrue(UbicacionesUtils.validarLongitud(longitudRegia));
		assertFalse(UbicacionesUtils.validarLongitud(longitudNoOk));
	}

	@Test
	void testCoordenadasOk() {
		double longitudOk = 54;
		double longitudNoOk = -200;
		double latitudOk = 64;
		double latitudNoOk = -97;

		// Deberíamos hacer 16 pruebitas :/
		assertTrue(UbicacionesUtils.validarCoordenadas(longitudOk, latitudOk));
		assertFalse(UbicacionesUtils.validarCoordenadas(longitudNoOk, latitudNoOk));
	}

	@Test
	void testCoordenadasFueraDelPlaneta() {
		double longitudOk = 54;
		double longitudNoOk = -200;
		double latitudOk = 64;
		double latitudNoOk = -97;

		// Deberíamos hacer 16 pruebitas :/
		assertFalse(UbicacionesUtils.validarCoordenadasFueraDelPlaneta(longitudOk, latitudOk));
		assertTrue(UbicacionesUtils.validarCoordenadasFueraDelPlaneta(longitudNoOk, latitudNoOk));
	}

	@Test
	void testSiEsHemisferioNorte() {
		double latitudOk = 64;
		double latitudNoOk = -97 ;
		double longitudOk = 10;

		Muestra m = new Muestra();
		m.setLatitud(latitudOk);
		m.setLongitud(-5555);

		assertFalse(UbicacionesUtils.validarSiEsHemisferioNorte(latitudNoOk, longitudOk));
		assertTrue(UbicacionesUtils.validarSiEsHemisferioNorte(latitudOk, longitudOk));
		assertFalse(UbicacionesUtils.validarSiEsHemisferioNorte(Math.abs(latitudNoOk), longitudOk));
		assertFalse(UbicacionesUtils.validarSiEsHemisferioNorte(m.getLatitud(), m.getLongitud()));
	}


	//Validar que una meustra de -35 dé color AMARILLO (este escenario esta mal a proposito para que vean que no siempre armar escenarios esta bien)
    //Validar que una muestra de 180 NO dé color AMARILLO
	@Test
	void testValidarColorAmarillo1(){
		Muestra m = new Muestra();
		m.setAlturaNivelMar(-35);
		assertTrue(ColoresUtils.clasificarMuestraPorAltura(m).equals("AMARILLO"));
	}
	@Test
	void testValidarColorAmarillo2(){
		Muestra m = new Muestra();
		m.setAlturaNivelMar(180);
		ColoresUtils.clasificarMuestraPorAltura(m);
		assertFalse(ColoresUtils.clasificarMuestraPorAltura(m).equals("AMARILLO"));

	}


	//Validar que una muestra de 10 dé color VERDE
    //Validar que una muestra de -100 NO dé color VERDE
	@Test
	void testValidarColorVerde1(){
		Muestra m = new Muestra();
		m.setAlturaNivelMar(10);
		ColoresUtils.clasificarMuestraPorAltura(m);
		assertTrue(ColoresUtils.clasificarMuestraPorAltura(m).equals("VERDE"));

	}

	@Test
	void testValidarColorVerde2(){
		Muestra m = new Muestra();
		m.setAlturaNivelMar(-100);
		ColoresUtils.clasificarMuestraPorAltura(m);
		assertFalse(ColoresUtils.clasificarMuestraPorAltura(m).equals("VERDE"));

	}


	//Validar que una muestra de 120 dé ROJO
	@Test
	void testValidaruMuestraColorRojo(){
		Muestra m = new Muestra();
		m.setAlturaNivelMar(120);
		ColoresUtils.clasificarMuestraPorAltura(m);
		assertTrue(ColoresUtils.clasificarMuestraPorAltura(m).equals("ROJO"));
	}


    


}
