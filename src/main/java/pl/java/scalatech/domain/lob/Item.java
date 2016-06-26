package pl.java.scalatech.domain.lob;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.Session;
import org.springframework.util.StreamUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@Table(name="ITEM_LOB")
public class Item extends AbstractEntity {

    private static final long serialVersionUID = 8435475355442015594L;

    @Lob
    protected java.sql.Blob image;

    @Lob
    protected java.sql.Clob description;

    @SneakyThrows
   public void get(){
       Item item = new Item();
       InputStream imageDataStream = item.getImage().getBinaryStream();
       ByteArrayOutputStream outStream = new ByteArrayOutputStream();
       StreamUtils.copy(imageDataStream, outStream);
       byte[] imageBytes = outStream.toByteArray();
       log.info("{}",imageBytes.length);
   }
    @SneakyThrows
    public void save(){

        Session session = null  ;
        FileInputStream fis = new FileInputStream("source.jpg");
        InputStream is = new BufferedInputStream(fis);
        int length = is.available();
        //NullPointer now
        Item item = session.get(Item.class, 1l);
        Blob blob = session.getLobHelper().createBlob(is, length);
        item.setImage(blob);
    }

}