# Refactoring_Project_Software

# Code Smells

## Long Method

- Na classe [EmployeeList](https://github.com/JoseGomesJr/Projeto_de_Software/blob/main/src/EmployeeList.java)
Métodos:
 [ChangerEmployee()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/EmployeeList.java#L178), [undo()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/EmployeeList.java#L310) e [redo()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/EmployeeList.java#L403) muito extensos, com muitos ifs e elses.

 Para resolver esses problemas foi usado o padrões complexos usando polimorfismo e hierarquia. Foi criada uma interface com o metetodo execute(), depois para cada tomada de decisão principal foi criada uma classe que implementa a interface dessa forma o codigo pode tomar diferentes comportamentos.

 Metodos refatorados:

## Feature Envy

- Na classe [Undo](https://github.com/JoseGomesJr/Projeto_de_Software/blob/main/src/Undo.java)
Métodos:
 [undotime()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/Undo.java#L119), [redotime()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/Undo.java#L139) , [undotype()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/Undo.java#L187).

 Esses metodos na classe Undo usa mais atributos e métodos da classe EmloyeeList e AuxEmployee. Foi usado um Move Method para a classe AuxEmployee.

 Metodos refatorados em suas respectivas classes:

## Speculative generality

- Na classe [AuxEmployee](https://github.com/JoseGomesJr/Projeto_de_Software/blob/main/src/AuxEmployee.java)
Métodos:
 [day()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/AuxEmployee.java#L142).

 Foi usado Remove Method, como o método se faz desnecessário, o removeremos do código em detrimento de métodos em uso.

## Codigo Repetido
 Método payMent() nas classes Comissioned, Hourly e Salaried apresenta o código com similaridades entre si.
 Para corrigir foi usado Extract Method para os passos comum aos métodos. Na classe "pai" Employee.
