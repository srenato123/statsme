package com.example.admin.stats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GradesFragment extends Fragment {


    public GradesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grades, container, false);
        String HTML_studentGrades = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "\t<style type=\"text/css\">\n" +
                "\t\ttable {\n" +
                "\t\t    border-collapse: collapse;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\ttable, th, td {\n" +
                "\t\t\tpadding: 10px;\n" +
                "\t\t    border: 1px solid black;\n" +
                "\t\t}\n" +
                "\t\t.number{\n" +
                "\t\t\ttext-align: right;\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<table style=\"\">\n" +
                "\t\t<thead>\n" +
                "\t\t\t<th>Subject Code</th>\n" +
                "\t\t\t<th>Description</th>\n" +
                "\t\t\t<th>Units</th>\n" +
                "\t\t\t<th>Prelim</th>\n" +
                "\t\t\t<th>Midterm</th>\n" +
                "\t\t\t<th>Prefinal</th>\n" +
                "\t\t\t<th>Final</th>\n" +
                "\t\t</thead>\n" +
                "\t\t<tbody>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>Math101</td>\n" +
                "\t\t\t\t<td>This is about algebra</td>\n" +
                "\t\t\t\t<td class='number'>2.0</td>\n" +
                "\t\t\t\t<td class='number'>76</td>\n" +
                "\t\t\t\t<td class='number'>65</td>\n" +
                "\t\t\t\t<td class='number'>74</td>\n" +
                "\t\t\t\t<td class='number'>81</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>English Language</td>\n" +
                "\t\t\t\t<td>English is fun</td>\n" +
                "\t\t\t\t<td class='number'>3.0</td>\n" +
                "\t\t\t\t<td class='number'>76</td>\n" +
                "\t\t\t\t<td class='number'>65</td>\n" +
                "\t\t\t\t<td class='number'>74</td>\n" +
                "\t\t\t\t<td class='number'>81</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t</tbody>\n" +
                "\t</table>\n" +
                "</body>\n" +
                "</html>";

        WebView studentgrades = (WebView) view.findViewById(R.id.studentgrades);
        studentgrades.loadData(HTML_studentGrades, "text/html; charset=UTF-8", null);

        String HTML_studentGradeDetails = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "\t<style type=\"text/css\">\n" +
                "\t\ttable {\n" +
                "\t\t    border-collapse: collapse;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\ttable, th, td {\n" +
                "\t\t\tpadding: 10px;\n" +
                "\t\t    border: 1px solid black;\n" +
                "\t\t}\n" +
                "\t\t.number{\n" +
                "\t\t\ttext-align: right;\n" +
                "\t\t}\n" +
                "\t</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<table style=\"\">\n" +
                "\t\t<thead>\n" +
                "\t\t\t<th>Quiz 1</th>\n" +
                "\t\t\t<th>Quiz 2</th>\n" +
                "\t\t\t<th>Task Performance</th>\n" +
                "\t\t\t<th>Exam</th>\n" +
                "\t\t\t<th>Final Grade</th>\n" +
                "\t\t</thead>\n" +
                "\t\t<tbody>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td class='number'>9</td>\n" +
                "\t\t\t\t<td class='number'>15</td>\n" +
                "\t\t\t\t<td class='number'>50</td>\n" +
                "\t\t\t\t<td class='number'>81</td>\n" +
                "\t\t\t\t<td class='number'>90</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t</tbody>\n" +
                "\t</table>\n" +
                "</body>\n" +
                "</html>";

        WebView studentgradesdetails = (WebView) view.findViewById(R.id.studentgradesdetails);
        studentgradesdetails.loadData(HTML_studentGradeDetails, "text/html; charset=UTF-8", null);

        return view;
    }

}
