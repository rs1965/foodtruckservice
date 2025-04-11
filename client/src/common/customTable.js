import React from "react";
import BootstrapTable from "react-bootstrap-table-next";
import paginationFactory from "react-bootstrap-table2-paginator";
import filterFactory from "react-bootstrap-table2-filter";
import "bootstrap/dist/css/bootstrap.min.css";
import "react-bootstrap-table-next/dist/react-bootstrap-table2.css";
import "react-bootstrap-table2-paginator/dist/react-bootstrap-table2-paginator.min.css";
import "react-bootstrap-table2-filter/dist/react-bootstrap-table2-filter.min.css";
const CustomTable = (props) => {
    const { columns, tableData, styleTable, tableHeight, bodyClasses, headerClasses,handleRowClick } = props;

    // Define pagination options
    const paginationOptions = {
        sizePerPage: 3, // Display 3 records per page
        sizePerPageList: [3, 10, 25, 50], // List of available page sizes
    };

    // Return the Bootstrap table with pagination and filtering
    return (
        <div style={{ height: tableHeight, overflowY: "auto", overflowX: 'hidden' }}>
            <BootstrapTable
                keyField="objectid"
                data={tableData}
                columns={columns}
                pagination={paginationFactory(paginationOptions)}
                filter={filterFactory()}
                striped
                hover
                bootstrap4
                className={styleTable}
                noDataIndication="No data available"
                rowEvents={{ onClick: (e, row) => handleRowClick(row) }}
            />
        </div>
    );
};

export default CustomTable;