import React from "react";
import BootstrapTable from "react-bootstrap-table-next";
import paginationFactory from "react-bootstrap-table2-paginator";
import filterFactory, { textFilter } from "react-bootstrap-table2-filter";
import "bootstrap/dist/css/bootstrap.min.css";
import "react-bootstrap-table-next/dist/react-bootstrap-table2.css";
import "react-bootstrap-table2-paginator/dist/react-bootstrap-table2-paginator.min.css";
import "react-bootstrap-table2-filter/dist/react-bootstrap-table2-filter.min.css";

const CustomTable = (props) => {
    const { columns, tableData, styleTable } = props;

    // Return the Bootstrap table with pagination and filtering
    return (
        <BootstrapTable
            keyField="objectid"
            data={tableData}
            columns={columns}
            pagination={paginationFactory()}
            filter={filterFactory()}
            striped
            hover
            bootstrap4
            className={styleTable}
            noDataIndication="No data available"
        />
    );
};

export default CustomTable;