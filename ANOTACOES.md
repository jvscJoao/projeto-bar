- Contrutores em vez da anotação @Autowired
    Pelas pesquisas, desta forma facilita a escrita de testes unitários, além de facilitar caso você esqueça de instanciamos.

- Sort
    Usado para organizar lista por propriedade(s) e ordem ascendente ou descendente

- URI
    URI(Uniform Resource Identifier - Identificador de Recurso Uniforme), usado no sistema para informar a localização do novo recurso que foi criado.Exemplo usado no sistema:
        URI locator = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newObj.getId())
        .toUri();