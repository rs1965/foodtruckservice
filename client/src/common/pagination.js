import React from 'react'
import { Pagination } from "react-bootstrap";
function PaginationCanvas(props) {
    const { data, currentPage, handlePageChange, getPaginationRange, totalPages } = props;
    return (
        <>
            {/* Pagination Controls */}
            {data.length > 0 && <Pagination>
                {/* Previous button */}
                <Pagination.Prev
                    disabled={currentPage === 1}
                    onClick={() => handlePageChange(currentPage - 1)}
                />

                {getPaginationRange().map((pageNumber) => (
                    <Pagination.Item
                        key={pageNumber}
                        active={currentPage === pageNumber}
                        onClick={() => handlePageChange(pageNumber)}
                    >
                        {pageNumber}
                    </Pagination.Item>
                ))}

                {/* Next button */}
                <Pagination.Next
                    disabled={currentPage === totalPages}
                    onClick={() => handlePageChange(currentPage + 1)}
                />
            </Pagination>}
        </>
    )
}

export default PaginationCanvas
