## Задача о городах
Пусть у нас есть группа островов, которые пронумерованы от 0 до V, между некоторыми городами построены мосты. Легко представить такую схему в виде графа, где вершинами являются острова, а рёбрами - мосты.
Будем считать, что из одного острова можно добраться до другого на автомобиле, если можно проложить такой путь по островам, перемещаясь с одного на другой если между ними есть мост.
Вам требуется для заданного графа островов для каждого острова посчитать до скольки островов можно доехать с него на машине.
Например, если у нас есть граф из шести островов и мосты построены между 0м и 1м, 1м и 2м, 3м и 4м островами, то из 0го острова можно добраться до двух островов (до 1го и до 2го через 1й), из 3го можно добраться только до одного острова (4го), а из пятого вообще мостов нет. Ответ вашей программы в таком случае должен быть: `Сколько достижимо городов для каждого города: [2, 2, 2, 1, 1, 0]`.
Давайте представим граф следующим образом: заведём массив на V элементов, в каждой ячейке будет лежать множество из вершин, соседствующих той, у которой номер совпадает с номером ячейки (в целях упрощения написания кода, в качестве множества можете брать и массивы).
```
Graph:
  vertices_info - изначально массив из V пустых множеств / списков
  
  add_edge(a, b):
    vertices_info[a].add(b)
    vertices_info[b].add(a)
  
  adjacent(v):
    return vertices_info[v]
  
  vertices():
    return номера от 0 до V
  
  size():
    return V
```
Создадим для нашего примера граф:
```
graph = Graph(V=6)
graph.add_edge(0, 1)
graph.add_edge(1, 2)
graph.add_edge(3, 4)
```
Заметим, что для каждого острова в качестве ответа подойдёт размер компоненты связности, в которой он находится, уменьшенный на 1 (для каждого острова мы не учитываем сам этот остров как тот до которого можно добраться). Поэтому сгодится модифицированный обход в глубину для нахождения компонент связности.
Теперь обход будет не только помечать компоненты номерами компонент связности (начнём нумерацию с 1, если же стоит у вершины 0, значит мы её ещё не размечали), но и возвращать количество вершин, которое он обошёл. Таким образом, мы сможем получить размеры компонент связности и запомнить их для каждой компоненты в массиве. После обхода нам будет достаточно прогуляться по массиву с информацией о том какая вершина принадлежит какой компоненте связности и заполнить массив для ответа задачи (где для каждой вершины хранится количество вершин, которые из неё достижимы).
```
dfs(graph, v, mark, marks):
  marks[v] = mark
  size = 1
  for vv in graph.adjacent(v):
    if в marks[vv] лежит 0:
      size += dfs(vv, mark, marks)
      size += dfs(graph, vv, mark, marks)
  return size
calc_paths(graph):
  marks = [V нулей]
  mark_sizes = [0]
  for v от 0 до V
    if в marks[v] лежит 0
      next_mark = длина(mark_sizes)
      size = dfs(graph, v, next_mark, marks)
      добавить size в конец mark_sizes
  answer = [V нулей]
  for i от 0 до V
    answer[i] = mark_sizes[marks[v]] - 1
  напечатать "Сколько достижимо городов для каждого города: " answer
```
