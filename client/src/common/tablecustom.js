import { CompactTable } from '@table-library/react-table-library/compact';
import { useTheme } from '@table-library/react-table-library/theme';
import { getTheme } from '@table-library/react-table-library/baseline';

const TableCustom = (props) => {
    const { columns, nodes } = props;
    const data = { nodes };
    const theme = useTheme(getTheme());
    return <CompactTable columns={columns} data={data} theme={theme} />;
};

export default TableCustom;