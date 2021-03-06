package com.psycorp.model.entity;

import lombok.Data;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

/**
 * Расчитанные результаты совместимости двух юзеров
 */
@Data
//@ToString(exclude = {"id"})
@Document
//вообще лучше индексы создавать в mongo shell или Compass, здесь их пишу пока просто для того, чтобы понимать, какие нужны индексы
@CompoundIndexes({@CompoundIndex(name = "users.$id", def = "{'users.$id' : 1}"),
        @CompoundIndex(name = "matches.result.number", def = "{'matches.result.number' : -1}")})

public class UserMatch extends AbstractEntity
{
    @Id
    private ObjectId id;


    @DBRef
    //TODO Если здесь поставить @Indexed, то создастся индекс на user, а не на user.id (user.name). Поэтому приходится использовать @CompoundIndex
    private Set<User> users;
//    private Set<String> userNames;
//    private MatchMethod matchMethod;

    private List<Match> matches;
}
