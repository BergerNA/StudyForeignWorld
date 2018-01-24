package db;

/**
 * Запросы к Базе данных к mydb
 */
final class SQLquery {

    final static String AddLesson = "INSERT INTO mydb.sets (id_user, name_Set) VALUES (";

    final static String delLesson = "DELETE FROM mydb.sets WHERE id_set = ";

    final static String updLesson1 = "UPDATE mydb.sets SET name_Set = '";
    final static String updLesson2 = "' WHERE id_set = ";

    /**
     * Получение таблиц
     */

    final static String getUsers = "Select * from mydb.user";

    final static String getLesson = "Select * from mydb.sets";

    final static String getUserLesson = "Select * from mydb.sets where mydb.sets.id_user = ";

    final static String getLevelKnow = "SELECT * FROM mydb.levelknowledge;";

    final static String getCommonWord = "Select * from mydb.commonword";

    final static String getForingWord = "SELECT    \tmydb.foringword.*,\n" +
                                                "\t\tmydb.translate.id_translateWord\n" +
                                        "FROM\n" +
                                                "\t\tmydb.foringword,\n" +
                                            "        mydb.translate\n" +
                                        "WHERE \t\n" +
                                                "\t\tmydb.foringword.id_foringWord = mydb.translate.id_foringWord;";

    final static String getTranslateWord = "Select * from mydb.translateword";

    final static String getTranslate = "Select * from mydb.translate";

    final static String getUsersWord = "Select * from mydb.userwords";

    final static String getSentenceExample = "Select * from mydb.sentenceexample";

    final static String getWordSentenceExample = "Select * from mydb.wordsentenceexample";

    final static String getSynonym = "Select * from mydb.synonym";

    final static String getAntonym = "Select * from mydb.antonym";

    final static String getPartSpeech = "SELECT * FROM mydb.partspeech;";

    final static String getUserWord = "SELECT  mydb.foringword.foringWord, \n" +
                "\t\tmydb.commonword.commonWord, \n" +
                "\t\tmydb.levelknowledge.levelKnowledge, \n" +
            "        mydb.translateword.translateWord,\n" +
            "        mydb.userwords.id_user\n" +
            "FROM \t\n" +
                "\t\tmydb.foringword,\n" +
                "\t\tmydb.commonword,\n" +
            "        mydb.levelknowledge,\n" +
            "        mydb.translateword,\n" +
            "        mydb.translate,\n" +
            "        mydb.userwords\n" +
            "WHERE\t\t\n" +
            "        mydb.userwords.id_foringWord = mydb.foringword.id_foringWord and\n" +
            "        mydb.commonword.id_commonWord = mydb.foringword.id_commonWord and\n" +
            "        mydb.levelknowledge.id_levelKnowledge = mydb.userwords.id_levelKnowledge and\n" +
            "        mydb.foringword.id_foringWord = mydb.translate.id_foringWord and\n" +
            "        mydb.translate.id_translateWord = mydb.translateword.id_translateWord and\n" +
            "        mydb.userwords.id_user = ";

    final static String getLessonWord_idSet = "SELECT  mydb.foringword.id_foringWord,\n" +
            "\t\tmydb.foringword.foringWord, \n" +
            "\t\tmydb.commonword.commonWord, \n" +
            "        mydb.foringword.id_commonWord, \n" +
            "\t\tmydb.levelknowledge.id_levelKnowledge, \n" +
            "        mydb.translateword.translateWord,\n" +
            "        mydb.userwords.id_user,\n" +
            "        mydb.userwords.count,\n" +
            "        mydb.userwords.dateRepeat,\n" +
            "        mydb.userwords.id_userWords\n" +
            "FROM \t\n" +
            "\t\tmydb.foringword,\n" +
            "\t\tmydb.commonword,\n" +
            "        mydb.levelknowledge,\n" +
            "        mydb.translateword,\n" +
            "        mydb.translate,\n" +
            "        mydb.userwords,\n" +
            "        mydb.setwords\n" +
            "WHERE\t\t\n" +
            "        mydb.userwords.id_foringWord = mydb.foringword.id_foringWord and\n" +
            "        mydb.commonword.id_commonWord = mydb.foringword.id_commonWord and\n" +
            "        mydb.levelknowledge.id_levelKnowledge = mydb.userwords.id_levelKnowledge and\n" +
            "        mydb.foringword.id_foringWord = mydb.translate.id_foringWord and\n" +
            "        mydb.translate.id_translateWord = mydb.translateword.id_translateWord and\n" +
            "        mydb.setwords.id_userWords = mydb.userwords.id_userWords and\n" +
            "\t\tmydb.setwords.id_set = ";

    final static String getLessonWord_finish = " group by mydb.foringword.foringWord;";
}
