package entidades;

import java.util.Objects;

/**
 *
 * @author usuario
 */
public class Marca {

    private Integer id;
    private String descricao;

    public Marca(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Marca(String descricao) {
        this.descricao = descricao;
    }

    public Marca(String[] csv) {
        id = Integer.parseInt(csv[0]);
        descricao = csv[1];
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String toCSV() {
        return "" + id + ";"
                + descricao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Marca other = (Marca) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
    

}
