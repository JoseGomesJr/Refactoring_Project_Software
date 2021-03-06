# Refactoring_Project_Software

# Code Smells

## Long Method

- Na classe [EmployeeList](https://github.com/JoseGomesJr/Projeto_de_Software/blob/main/src/EmployeeList.java)
Métodos:
 [ChangerEmployee()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/EmployeeList.java#L178), [undo()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/EmployeeList.java#L310) e [redo()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/EmployeeList.java#L403) muito extensos, com muitos ifs e elses.

 Para resolver esses problemas foi usado o padrões complexos usando polimorfismo e hierarquia. Foi criada uma interface com o metetodo execute(), depois para cada tomada de decisão principal foi criada uma classe que implementa a interface dessa forma o codigo pode tomar diferentes comportamentos.

 Metodos refatorados:
 [ChangerEmployee()](https://github.com/JoseGomesJr/Refactoring_Project_Software/blob/5b8ce8422bbaa035fe22780078e8c3d491155eda/src/App/EmployeeList.java#L185), [undo()](https://github.com/JoseGomesJr/Refactoring_Project_Software/blob/5b8ce8422bbaa035fe22780078e8c3d491155eda/src/App/EmployeeList.java#L236) e [redo()](https://github.com/JoseGomesJr/Refactoring_Project_Software/blob/5b8ce8422bbaa035fe22780078e8c3d491155eda/src/App/EmployeeList.java#L244).


## Feature Envy

- Na classe [Undo](https://github.com/JoseGomesJr/Projeto_de_Software/blob/main/src/Undo.java)
Métodos:
 [undotime()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/Undo.java#L119), [redotime()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/Undo.java#L139) , [undotype()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/Undo.java#L187).

 Esses metodos na classe Undo usa mais atributos e métodos da classe EmployeeList e AuxEmployee. Foi usado um Move Method para a classe AuxEmployee.

 Metodos refatorados em suas respectivas classes:
 [undotime()](https://github.com/JoseGomesJr/Refactoring_Project_Software/blob/5b8ce8422bbaa035fe22780078e8c3d491155eda/src/model/Undo/SalveTime.java#L34), [redotime()](https://github.com/JoseGomesJr/Refactoring_Project_Software/blob/5b8ce8422bbaa035fe22780078e8c3d491155eda/src/model/Undo/SalveTime.java#L38) , [undotype()](https://github.com/JoseGomesJr/Refactoring_Project_Software/blob/5b8ce8422bbaa035fe22780078e8c3d491155eda/src/model/Undo/SalveCommission.java#L17).

## Speculative generality

- Na classe [AuxEmployee](https://github.com/JoseGomesJr/Projeto_de_Software/blob/main/src/AuxEmployee.java)
Métodos:
 [day()](https://github.com/JoseGomesJr/Projeto_de_Software/blob/12b43ab611548100b7988f6bff4c6cf3553b7d8b/src/AuxEmployee.java#L142).

 Foi usado Remove Method, como o método se faz desnecessário, o removeremos do código em detrimento de métodos em uso.

## Codigo Repetido
 Método [payMent()](https://github.com/JoseGomesJr/Refactoring_Project_Software/blob/a03a87a33bcfdd0ebdddc7f041271937a8c7c174/src/model/Employee/Commissioned.java#L50) nas classes Comissioned, Hourly e Salaried apresenta o código com similaridades entre si.
 Para corrigir foi usado Extract Method para os passos comum aos métodos. Na classe "pai" Employee.

## Primitive obcession
 O atributo address na classe Employee está sendo tratado como uma string. Replace data value with object, criar um objeto endereço associado à classe empregado com todos os atributos necessários.
