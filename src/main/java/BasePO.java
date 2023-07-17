import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;

import java.io.Serializable;
import java.util.Date;

public class BasePO implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Boolean deleteFlag;

    @Version
    private Integer version;

}
