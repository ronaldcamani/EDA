# Laboratorio 07 - Árboles AVL

## Descripción
Implementación completa de un Árbol AVL (Adelson-Velsky y Landis) genérico en Java con funcionalidades de inserción, eliminación, búsqueda, balanceo automático y visualización gráfica.

## Archivos del Proyecto

### Clases Principales
- **`NodeAVL.java`**: Clase genérica para los nodos del árbol AVL
- **`AVLTree.java`**: Implementación completa del árbol AVL genérico
- **`AVLTreeVisualizer.java`**: Visualizador usando Swing (alternativo)
- **`GraphStreamAVLVisualizer.java`**: Visualizador usando GraphStream (cuando esté disponible)

### Clases de Prueba
- **`TestAVL.java`**: Demostración del Ejercicio 1
- **`MenuTestAVL.java`**: Programa con menú interactivo para el Ejercicio 2
- **`GraphicalTestAVL.java`**: Programa de visualización gráfica para el Ejercicio 3
- **`QuickTest.java`**: Prueba rápida de todas las funcionalidades

## Funcionalidades Implementadas

### Operaciones Básicas
- ✅ `insert(x)` - Inserción con balanceo automático
- ✅ `remove(x)` - Eliminación con balanceo automático
- ✅ `search(x)` - Búsqueda de elementos
- ✅ `destroy()` - Destruir el árbol
- ✅ `isEmpty()` - Verificar si está vacío

### Operaciones de Consulta
- ✅ `min()` - Encontrar elemento mínimo
- ✅ `max()` - Encontrar elemento máximo
- ✅ `predecessor(x)` - Encontrar predecesor
- ✅ `successor(x)` - Encontrar sucesor

### Recorridos
- ✅ `inOrder()` - Recorrido en orden
- ✅ `preOrder()` - Recorrido pre-orden
- ✅ `postOrder()` - Recorrido post-orden

### Balanceo y Rotaciones
- ✅ `balancearIzquierda()` - Balancear hacia la izquierda
- ✅ `balancearDerecha()` - Balancear hacia la derecha
- ✅ `rotacionSimpleIzquierda()` - Rotación simple a la izquierda
- ✅ `rotacionSimpleDerecha()` - Rotación simple a la derecha

### Visualización
- ✅ Visualización en consola con estructura de árbol
- ✅ Visualización gráfica con Swing
- ✅ Soporte para GraphStream (cuando esté disponible)

## Cómo Ejecutar

### Compilación
```bash
javac *.java
```

### Ejercicio 1 - Demostración paso a paso
```bash
java TestAVL
```

### Ejercicio 2 - Menú interactivo
```bash
java MenuTestAVL
```

### Ejercicio 3 - Visualización gráfica
```bash
java GraphicalTestAVL
```

### Prueba rápida
```bash
java QuickTest
```

## Configuración de GraphStream (Opcional)

Para usar la visualización avanzada con GraphStream, agregue las siguientes dependencias:

### Maven
```xml
<dependency>
    <groupId>org.graphstream</groupId>
    <artifactId>gs-core</artifactId>
    <version>2.0</version>
</dependency>
<dependency>
    <groupId>org.graphstream</groupId>
    <artifactId>gs-ui-swing</artifactId>
    <version>2.0</version>
</dependency>
```

### Gradle
```gradle
implementation 'org.graphstream:gs-core:2.0'
implementation 'org.graphstream:gs-ui-swing:2.0'
```

### Descargar JARs manualmente
1. Descargar `gs-core-2.0.jar` desde [GraphStream Releases](https://github.com/graphstream/gs-core/releases)
2. Descargar `gs-ui-swing-2.0.jar` desde [GraphStream UI Swing](https://github.com/graphstream/gs-ui-swing/releases)
3. Compilar con classpath:
```bash
javac -cp "gs-core-2.0.jar:gs-ui-swing-2.0.jar:." *.java
java -cp "gs-core-2.0.jar:gs-ui-swing-2.0.jar:." GraphicalTestAVL
```

## Características Técnicas

### Genéricos
- Todas las clases y métodos son genéricos (`<T extends Comparable<T>>`)
- Funciona con cualquier tipo que implemente `Comparable`

### Balanceo Automático
- Factor de balance calculado automáticamente
- Rotaciones aplicadas cuando es necesario
- Mantiene la propiedad AVL en todo momento

### Visualización Inteligente
- Detecta automáticamente si GraphStream está disponible
- Se degrada graciosamente a visualización Swing si no está disponible
- Muestra nodos con sus valores y alturas

## Ejercicios Implementados

### Ejercicio 1
- Inserción de secuencia: 100, 200, 300, 400, 500, 50, 25, 350, 375, 360, 355, 150, 175, 120, 190
- Muestra paso a paso con rotaciones
- Eliminación de todos los nodos
- Recorridos InOrder, PreOrder, PostOrder

### Ejercicio 2
- Menú interactivo con todas las operaciones
- Implementación de métodos específicos requeridos
- Validación de entrada y manejo de errores

### Ejercicio 3
- Visualización gráfica del árbol
- Soporte para GraphStream y alternativa Swing
- Visualización en tiempo real de cambios

## Ejemplo de Uso

```java
// Crear un árbol AVL de enteros
AVLTree<Integer> avl = new AVLTree<>();

// Insertar elementos
avl.insert(50);
avl.insert(30);
avl.insert(70);

// Buscar elemento
boolean existe = avl.search(30); // true

// Obtener min/max
Integer min = avl.min(); // 30
Integer max = avl.max(); // 70

// Mostrar recorridos
avl.inOrder();   // 30 50 70
avl.preOrder();  // 50 30 70
avl.postOrder(); // 30 70 50

// Visualizar gráficamente
GraphStreamAVLVisualizer.showAVLTree(avl);
```

## Notas Importantes

1. **Sin GraphStream**: La aplicación funciona perfectamente sin GraphStream, usando Swing como alternativa
2. **Genéricos**: Funciona con cualquier tipo comparable (Integer, String, etc.)
3. **Thread Safety**: La implementación no es thread-safe
4. **Memoria**: Usa referencias circulares que se limpian con `destroy()`

## Autor
Implementación para el Laboratorio 07 - EDA UNSA