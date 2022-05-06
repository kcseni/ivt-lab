package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTs1;
  private TorpedoStore mockTs2;

  @BeforeEach
  public void init(){
    
    this.mockTs1= mock(TorpedoStore.class); 
    this.mockTs2= mock(TorpedoStore.class); 
    this.ship = new GT4500(mockTs1, mockTs2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTs1.isEmpty()).thenReturn(false);
    when(mockTs2.isEmpty()).thenReturn(true);
    when(mockTs2.fire(1)).thenReturn(true);
    when(mockTs2.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result); 
    verify(mockTs1, times(1)).fire(1);

    verify(mockTs2, times(1)).fire(0);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    when(mockTs1.isEmpty()).thenReturn(false);
    when(mockTs2.isEmpty()).thenReturn(false);
    when(mockTs2.fire(1)).thenReturn(true);
    when(mockTs2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert

    verify(mockTs1, times(1)).fire(1);
    verify(mockTs2, times(1)).fire(1);
  }

}
