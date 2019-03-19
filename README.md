# Traductor de documento por medio de diccionario
Implementar un diccionario Inglés – Español, implementando un árbol binario **in-order**. Para alimentar el diccionario recibirá el archivo diccionario.txt que contiene ASOCIACIONES con la palabra en inglés y su equivalente en español. Ejemplo del contenido de este archivo es: 

```
(house, casa) 
(dog, perro) 
(homework, tarea) 
(woman, mujer) 
(town, pueblo) 
(yes, si) 
```
Al crear el árbol binario, se cargara un archivo, con contenido en inglés, el cual el programa traducira cada palabra al español. Si en dado caso la palabra no está en el diccionario, mantendra la palabra en inglés, encerrada entre asteriscos. **Se ha de aclarar que será una traducción lineal, donde no se tomara en cuenta la gramática y sintaxis del idioma inglés**. Por ejemplo:

* Si el archivo contiene:
```
The woman asked me to do my homework about my town.`
```

* Dando como resultado:
```
*The*  mujer  *asked*  *me*  *to*  *do*  *my*  tarea  *about*  *my*  pueblo. 
```


## Referencias

* Silva, D. (2009). _Comparable y Comparator_. Extraído de: https://www.apuntesdejava.com/2009/04/comparable-y-comparator.html

* Sierra, M. y Cuenca, J. (2006). _Ejercicio y Ejemplo Resuleto: Uso de la interface comparable y método compareto de Java. Comparar Objetos_. Extraído de: https://www.aprenderaprogramar.com/attachments/article/587/CU00913C%20Ejercicio%20ejemplo%20resuelto%20interfaz%20comparable%20java%20compareTo.pdf

## Autor

* **Pablo Sao** - [psao](https://github.com/psao)
