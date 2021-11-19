package com.billex.utils;



public interface FilePath {
    String userDir = System.getProperty("user.dir");
    String PDF_DIR_PATH = userDir+"\\src\\main\\resources\\pdf_files\\"; //KsebBill_1156202002116.pdf
    String CSV_DIR_PATH = userDir+"\\src\\main\\resources\\csv_files\\";
    String TEXT_DIR_PATH = userDir+"\\src\\main\\resources\\text_files\\";
}
